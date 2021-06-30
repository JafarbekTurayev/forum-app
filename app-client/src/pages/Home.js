import React from 'react';
import {Button} from "reactstrap";
import Login from "./Login";
import {useHistory} from "react-router";
import {Link} from "react-router-dom";

const Home = () => {
    let history = useHistory();

    const logout = () => {
        localStorage.setItem("token", null);
        history.push("/login")
    }
    return (
        <div>
            <div className="container">
                {
                    localStorage.getItem("token") !== null ?
                        <div>
                            <h1>Home ga xush kelibsz!</h1>
                            <Link to="/myQuestions">Mening Savollarim</Link>
                            <br/>
                            <Link to="questions">barcha Savollar</Link>
                            <br/>
                            <Button onClick={logout}>Chiqish</Button>
                        </div>
                        : <Login/>
                }
            </div>
        </div>
    );
};

export default Home;