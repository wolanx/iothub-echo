export class RequestUtil {

    static get(url, p = {}) {
        return fetch(url).then(resp => {
            const json = resp.json()
            if (resp.status >= 200 && resp.status < 300) {
                return json
            } else {
                return json.then(err => {
                    throw err
                })
            }
        })
    }

    static post(url, p) {
        return fetch(url, {
            "method": "post",
            "headers": {
                "content-type": "application/json;charset=UTF-8"
            },
            "credentials": "include",
            "body": JSON.stringify(p)
        }).then(resp => {
            const json = resp.json()
            if (resp.status >= 200 && resp.status < 300) {
                return json
            } else {
                return json.then(err => {
                    throw err
                })
            }
        })
    }

    static delete(url) {
        return fetch(url, {
            "method": "delete",
            "headers": {
                "content-type": "application/json;charset=UTF-8"
            },
            "credentials": "include",
        }).then(resp => {
            const json = resp.json()
            if (resp.status >= 200 && resp.status < 300) {
                return json
            } else {
                return json.then(err => {
                    throw err
                })
            }
        })
    }

}

