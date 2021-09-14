import {RequestUtil} from '/js/util.js'

const {useState} = React
const {Button, Modal, Form} = ReactBootstrap

$('.js-device-delete').click(function () {
    const did = $(this).data('id')
    console.log(did);
    if (confirm("Are you sure delete it ?")) {
        RequestUtil.delete(`/api/product/${did}`).then(({message}) => {
            alert(message)
            location.reload()
        })
    }
})

function App() {
    const [validated, setValidated] = useState(false)
    const [showCreate, setShowCreate] = useState(false)
    const [productKey, setProductKey] = useState()
    const [productName, setProductName] = useState()

    function handleSubmit(e) {
        e.preventDefault()
        e.stopPropagation()
        setValidated(true)
        if (e.currentTarget.checkValidity()) {
            RequestUtil.post("/api/product", {
                productKey,
                productName,
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
                <Button size="sm" onClick={() => setShowCreate(true)}>Add product</Button>
                <Modal show={showCreate} onHide={() => setShowCreate(false)}>
                    <Form noValidate validated={validated} onSubmit={handleSubmit}>
                        <Modal.Header closeButton>
                            <Modal.Title>Add product</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form.Group className="mb-3">
                                <Form.Label>Product Key</Form.Label>
                                <Form.Control size="sm" type="text" required value={productKey}
                                              onChange={({target: {value}}) => setProductKey(value)}/>
                                <Form.Control.Feedback type="invalid">Please check.</Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group controlId="validationCustom05">
                                <Form.Label>Product Name</Form.Label>
                                <Form.Control size="sm" type="text" required value={productName}
                                              onChange={({target: {value}}) => setProductName(value)}/>
                                <Form.Control.Feedback type="invalid">Please check.</Form.Control.Feedback>
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

ReactDOM.render(<App/>, document.getElementById('root'))
