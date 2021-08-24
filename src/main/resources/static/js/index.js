// import htm from 'https://unpkg.com/htm@3.1.0/dist/htm.module.js?module';
// import axios from 'https://unpkg.com/axios@0.2.1/dist/axios.min.js?module'
import { Request } from '/js/util.js'

const { useState } = React

$('.js-device-delete').click(function () {
  const did = $(this).data('id')
  console.log(did);
  Request.delete(`/api/device/${did}`).then(({ message, data: res, error }) => {
    console.log(res, error)
    alert(message)
    location.reload()
  })
})

function App() {
  const [showCreate, setShowCreate] = useState(false)
  const [productKey, setProductKey] = useState()
  const [deviceName, setDeviceName] = useState()
  const [deviceSecret, setDeviceSecret] = useState()

  function doCreate() {
    Request.post("/api/device", {
      productKey,
      deviceName,
      deviceSecret,
    }).then(({ data: res, error }) => {
      console.log(res, error)
      location.reload()
    })
  }

  return (
    <div>
      <button className="btn btn-sm btn-primary" onClick={() => setShowCreate(true)}>Add device</button>
      <div className="modal" tabIndex="-1" style={{ display: showCreate ? 'block' : '' }}>
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Add device</h5>
              <button type="button" className="close" onClick={() => setShowCreate(false)}>
                <span>&times;</span>
              </button>
            </div>
            <div className="modal-body">
              <div className="form-group">
                <label htmlFor="exampleInputEmail1">Product Key</label>
                <input type="text" className="form-control form-control-sm" id="exampleInputEmail1" value={productKey} onChange={({ target: { value } }) => setProductKey(value)} />
              </div>
              <div className="form-group">
                <label htmlFor="exampleInputEmail2">Device Name</label>
                <input type="text" className="form-control form-control-sm" id="exampleInputEmail2" value={deviceName} onChange={({ target: { value } }) => setDeviceName(value)} />
                <small className="form-text text-muted">Well never share your email with anyone else.</small>
              </div>
              <div className="form-group">
                <label htmlFor="exampleInputEmail3">Device Secret</label>
                <input type="text" className="form-control form-control-sm" id="exampleInputEmail3" value={deviceSecret} onChange={({ target: { value } }) => setDeviceSecret(value)} />
              </div>
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-sm btn-primary" onClick={doCreate}>OK</button>
            </div>
          </div>
        </div>
      </div>
      {showCreate && <div className="modal-backdrop fade show"></div>}
    </div>
  )
}

console.log(123);

ReactDOM.render(<App />, document.getElementById('root'))
