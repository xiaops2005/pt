/**
 * Created by xiaops on 2018/1/17.
 */
import React from 'react';
import { Tooltip, Card, Layout, Menu, Breadcrumb, Icon } from 'antd';
import {NavLink} from 'react-router-dom';
import Ellipsis from 'ant-design-pro/lib/Ellipsis';//在antd3.0以下版本不生效
// import 'ant-design-pro/dist/ant-design-pro.css';

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
      <Card noHovering>
        {movieList.map((movie) => {
          console.log(movie.img)
          console.log(movie.id)
          console.log(movie.name)
          const n = movie.name.substr(0,10);
          const href = '/ptfans/movie-detail/' + movie.id
          return(
          <Card.Grid style={gridStyle}>
            <Tooltip placement="leftTop" title={movie.name}>
              <NavLink to={href} target="_blank">
                <div className="custom-image">
                    <img width="100%" height="320px" src={movie.img} alt={movie.alt}/>
                </div>
                <div className="custom-card">
                  <Ellipsis lines={2}>{n}</Ellipsis>
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
