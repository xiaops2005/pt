/**
 * Created by viewhigh on 2017/6/12.
 */
import React, {Component} from 'react';
import APPCONFIG from 'constants/Config';

class EditableCellIcon extends React.Component {
  state = {
    value: this.props.value,
    editable: false,
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      value: nextProps.value,
      editable: nextProps.editable,
    });
  }

  render() {
    const { value } = this.state;
    var iconUrl = value.billClassification ?  APPCONFIG.PublicURL+"assets/demo/icon/"+value.billClassification+".png" : "";
    return (
      <div className="editable-cell-input-wrapper" >
        <img width={25} height={25} src={iconUrl}  />
      </div>
    );
  }
}
export default EditableCellIcon;
