import React from 'react';

import {Input, Select} from 'antd';

const Option = Select.Option;


class EditableCellInput extends React.PureComponent {
  shouldComponentUpdate(nextProps) {
    return JSON.stringify(this.props) !== JSON.stringify(nextProps)
  }

  render() {
    let {editable, value, onChange} = this.props;
    return (
      <div>
        {editable && <Input style={{margin: '-5px,0'}} value={value} onChange={e => onChange(e.target.value)}/>}
        {!editable && value}
      </div>
    )
  }
}

class EditableCellSelect extends React.PureComponent {
  shouldComponentUpdate(nextProps) {
    return JSON.stringify(this.props) !== JSON.stringify(nextProps)
  }

  render() {
    let {editable, value, options, mappedOptions, onChange, decodeVal, decodeDisplay} = this.props;
    if (decodeVal === undefined) {
      decodeVal = "codeId";
    }
    if (decodeDisplay === undefined) {
      decodeDisplay = "codeName";
    }
    if (editable) {
      return (
        <div>
          {<Select className="editable-cell-select" onChange={val => onChange(val)} disabled={!editable} value={value}>
            {
              options.map(function (item) {
                return <Option key={item[decodeVal]} value={item[decodeVal]}>{item[decodeDisplay]}</Option>;
              })
            }
          </Select>}
        </div>
      )
    } else if (mappedOptions !== undefined && mappedOptions !== {}) {
        if(typeof mappedOptions[value] == 'object'){
          return <div>{mappedOptions[value][decodeDisplay]}</div>;
        }else{
          return <div>{mappedOptions[value]}</div>;
        }
    } else if (options && options.length > 0) {
      let filter = options.filter(item => item[decodeVal] === value);
      if (filter.length > 0) {
        return <div>{filter[0][decodeDisplay]}</div>;
      }
    }
    return null;
  }
}

export {EditableCellInput, EditableCellSelect}
