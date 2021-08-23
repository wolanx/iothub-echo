// import htm from 'https://unpkg.com/htm@3.1.0/dist/htm.module.js?module';
// import axios from 'https://unpkg.com/axios@0.2.1/dist/axios.min.js?module'

function App () {
    function doCreate() {
        fetch("/api/admin/owner/3", {
            "headers": {
                "content-type": "application/json;charset=UTF-8"
            },
            "credentials": "include",
            "method": "post",
            "body": JSON.stringify({
                "access_expire_at": "2020-09-15 10:46:15"
            })
            }).then(res => res.json()).then(res => res.data)
        console.log(123);
    }
    return (
        <div>
            <button className="btn btn-sm btn-primary" onClick={doCreate}>添加</button>
        </div>
    )
}

console.log(123);

ReactDOM.render(<App/>, document.getElementById('root'))
