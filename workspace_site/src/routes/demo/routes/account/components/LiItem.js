/**
 * Created by viewhigh on 2017/6/1.
 */
import React, {Component} from 'react';


class LiItem extends Component {


  render() {
    return <div style={{paddingBottom:'6px'}}>
      <table style={{width: '100%'}}>
        <tr>
          <td style={{textAlign: 'left'}}><img width={25} height={25} src={this.props.pic}/></td>
          <td colSpan={3} style={{textAlign: 'center'}}>{this.props.title}</td>
          <td style={{textAlign: 'right'}}>{this.props.money}</td>
        </tr>
      </table>
    </div>
  }
}

export default LiItem;
