import React from 'react';
import Login from "./pages/Login";
import Home from "./pages/Home";
import MyQuestions from "./pages/MyQuestions"
import Questions from "./pages/Questions"
import {Route, Switch, BrowserRouter} from "react-router-dom"
import {useHistory} from "react-router";

const App = () => {
    let history = useHistory();
    return (
        <div>
            <h1>App</h1>
            <BrowserRouter>
                <Switch>
                    <Route component={Home} exact path={"/"}/>
                    <Route component={Login} exact path={"/login"}/>
                    <Route component={MyQuestions} exact path={"/myQuestions"}/>
                    <Route component={Questions} exact path={"/questions"}/>
                </Switch>
            </BrowserRouter>

        </div>
    );
};

export default App;