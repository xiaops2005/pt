package com.viewhigh.excel.validator;

import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.entity.ReIncome;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;


@Service
public class ReIncomeValidator extends LocalValidatorFactoryBean implements
		Validator {


	@Override
	public void validate(Object target, Errors errors) {
		BaseVO vo = (BaseVO) target;
		try {
			Object obj = vo.getObj();
			ReIncome reIncome = (ReIncome) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public ExecutableValidator forExecutables() {
		return null;
	}

	@Override
	public ParameterNameProvider getParameterNameProvider() {
		return null;
	}
}
