export class Request {

    static post(url, p) {
        return fetch(url, {
            "method": "post",
            "headers": {
                "content-type": "application/json;charset=UTF-8"
            },
            "credentials": "include",
            "body": JSON.stringify(p)
        }).then(res => res.json())
    }

    static delete(url) {
        return fetch(url, {
            "method": "delete",
            "headers": {
                "content-type": "application/json;charset=UTF-8"
            },
            "credentials": "include",
        }).then(res => res.json())
    }

}

