/**
 * Created by xiaops on 2018/1/17.
 */
import React from 'react';
import { Tooltip, Card, Layout, Menu, Breadcrumb, Icon } from 'antd';
import {NavLink} from 'react-router-dom';
import Ellipsis from 'ant-design-pro/lib/Ellipsis';//在antd3.0以下版本不生效
// import 'ant-design-pro/dist/ant-design-pro.css';
import '../index.css'
class MovieCard extends React.Component {
  constructor(props) {
    super(props);
  }

  // onClick = (e) =>{
  //   console.log(e)
  //   console.log(this.context)
  //   this.context.router.push({pathname: '/#/ptfans/movie-detail', query: {hello: 'world'}, state: {item: 'hello'}});
  // }

  render() {
    const {movieList} = this.props;
    const gridStyle = {
      width: '25%',
      textAlign: 'left',
    };
    return (
      <Card hoverable>
        {movieList.map((movie) => {
          const {smallImage, id, year, title} = movie
          const name = year + ' ' + title
          const href = '//movie-detail/' + id
          return(
          <Card.Grid style={gridStyle} key={id}>
            <Tooltip placement="leftTop" title={name}>
              <NavLink to={href} target="_blank">
                <div className="custom-image">
                  <picture>
                    <source width="100%" height="260" type="image/webp" srcSet={smallImage}/>
                    <img width="100%" height="260"  src={smallImage}/>
                  </picture>
                </div>
                <div className="custom-card">
                  <Ellipsis lines={2}>{name}</Ellipsis>
                </div>
              </NavLink>
            </Tooltip>
          </Card.Grid>)
          })
        }
      </Card>
    )
  }
}
export default MovieCard
