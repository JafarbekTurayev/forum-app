import React from 'react';
import {Button} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation"
import axios from "axios";
import {useHistory} from "react-router";

const Login = () => {
        let history = useHistory();
        const login = (event, values) => {
            console.log(values)

            //axios config
            axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
            axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
            axios.post("http://localhost:80/auth/login", values, {}).then(res => {
                    console.log(res)
                    localStorage.setItem("token", "Bearer " + res.data)
                    history.push("/")
                }
            )
        }

        return (
            <div>
                <AvForm onValidSubmit={login}>
                    {/* With AvField */}
                    <AvField name="username" label="UserName" required/>
                    <AvField name="password" label="Password" required/>
                    <Button type="submit">Kirish</Button>
                </AvForm>
                {console.log(localStorage.getItem("token"))}
            </div>
        );
    }
;

export default Login;