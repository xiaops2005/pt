import React from 'react';
import FileTemplate from './containers/FileTemplate'
import ColumnTemplate from './containers/ColumnTemplate'
class TemplateColumns extends React.Component {

  render() {
    return (
      <div>
        <FileTemplate/>
        <ColumnTemplate/>
      </div>
    )
  }
}

export default TemplateColumns


