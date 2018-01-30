package com.viewhigh.vadp.portal.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.common.utils.ShiroUtils;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;
import com.viewhigh.vadp.portal.sys.service.ShiroService;
import com.viewhigh.vadp.portal.sys.service.SysMenuService;
import com.viewhigh.vadp.portal.sys.service.SysUserService;
import com.viewhigh.vadp.portal.sys.service.SysUserTokenService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
public class SysLoginController extends AbstractController {
    //@Autowired
    private Producer captchaProducer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private ShiroService shiroService;
    /**
     * 验证码
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = captchaProducer.createText();
        //生成图片验证码
        BufferedImage image = captchaProducer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody SysUser login) throws IOException {
        //本项目已实现，前后端完全分离，但页面还是跟项目放在一起了，所以还是会依赖session
        //如果想把页面单独放到nginx里，实现前后端完全分离，则需要把验证码注释掉(因为不再依赖session了)
//		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//		if(!captcha.equalsIgnoreCase(kaptcha)){
//			return R.error("验证码不正确");
//		}
        String username = login.getUsername();
        String password = login.getPassword();
        //用户信息
        SysUser user = sysUserService.findByUsername(username);

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
//		if(user == null || !user.getPassword().equals(password)) {

            return new ResponseEntity<R>(R.error("账号或密码不正确"), HttpStatus.UNAUTHORIZED);
        }

        //账号锁定
        if (user.getStatus() == 0) {

            return new ResponseEntity<R>(R.error("账号已被锁定,请联系管理员"), HttpStatus.FORBIDDEN);
        }
//        Subject subject = ShiroUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        subject.login(token);
        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        r.put("user", user);
        return new ResponseEntity<R>(r, HttpStatus.OK);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/api/currentUser")
    public R info() {
        return R.ok().put("user", getUser());
    }

    @RequestMapping("/api/me/{token}")
    public R getUserByToken(@PathVariable("token") String token) {
        //根据accessToken，查询用户信息
        SysUserToken tokenEntity = shiroService.queryByToken(token);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw new IncorrectCredentialsException("token失效，请重新登录");
            return R.error("token失效，请重新登录");
        }

        //查询用户信息
        SysUser user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
            return R.error("账号已被锁定,请联系管理员");
        }

        return R.ok().put("user", user);
    }



    /**
     * 退出
     */
    @RequestMapping(value = "/api/logout", method = RequestMethod.POST)
    public R logout() {
        sysUserTokenService.logout(getUserId());
        Subject subject = ShiroUtils.getSubject();
        subject.logout();
        return R.ok();
    }


    /**
     * 导航菜单
     */
    @RequestMapping("/api/nav")
    public R nav(){
        List<SysMenu> menuList = menuService.findByUser(getUserId());
        return R.ok().put("menuList", menuList);
    }


}
