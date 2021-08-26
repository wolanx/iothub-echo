import { RequestUtil } from '/js/util.js'

const { useState } = React
const { Button, Modal, Form } = ReactBootstrap

$('.js-device-delete').click(function () {
  const did = $(this).data('id')
  console.log(did);
  RequestUtil.delete(`/api/device/${did}`).then(({ message }) => {
    alert(message)
    location.reload()
  })
})

function App() {
  const [validated, setValidated] = useState(false)
  const [showCreate, setShowCreate] = useState(false)
  const [productKey, setProductKey] = useState()
  const [deviceName, setDeviceName] = useState()
  const [deviceSecret, setDeviceSecret] = useState()

  function handleSubmit(e) {
    e.preventDefault()
    e.stopPropagation()
    setValidated(true)
    if (e.currentTarget.checkValidity()) {
      RequestUtil.post("/api/device", {
        productKey,
        deviceName,
        deviceSecret,
      }).then(res => {
        console.log(res)
        location.reload()
      }, err => {
        alert(err.message)
      })
    }
  }

  return (
    <div>
      <Button size="sm" onClick={() => setShowCreate(true)}>Add device</Button>
      <Modal show={showCreate} onHide={() => setShowCreate(false)}>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <Modal.Header closeButton>
            <Modal.Title>Add device</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form.Group className="mb-3">
              <Form.Label>Product Key</Form.Label>
              <Form.Control size="sm" type="text" required value={productKey} onChange={({ target: { value } }) => setProductKey(value)} />
              <Form.Control.Feedback type="invalid">Please choose a username.</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Device Name</Form.Label>
              <Form.Control size="sm" type="text" required value={deviceName} onChange={({ target: { value } }) => setDeviceName(value)} />
              <Form.Control.Feedback type="invalid">Please choose a username.</Form.Control.Feedback>
              {/* <Form.Text>We'll never share your email with anyone else.</Form.Text> */}
            </Form.Group>
            <Form.Group controlId="validationCustom05">
              <Form.Label>Device Secret</Form.Label>
              <Form.Control size="sm" type="text" required value={deviceSecret} onChange={({ target: { value } }) => setDeviceSecret(value)} />
              <Form.Control.Feedback type="invalid">Please choose a username.</Form.Control.Feedback>
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button type="submit" variant="primary">OK</Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </div>
  )
}

ReactDOM.render(<App />, document.getElementById('root'))
