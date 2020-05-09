import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListUserComponent from "./user/ListUserComponent";
import AddUserComponent from "./user/AddUserComponent";
import EditUserComponent from "./user/EditUserComponent";
import React from "react";

const style = {
    color: 'red',
    margin: '10px'
}

const AppRouter = () => {
    return(
        <div>
            <Router>
                <div className="col-md-6">
                    <h1 className="text-center" style={style}>React User Application</h1>
                    <Switch>
                        <Route path="/" exact component={ListUserComponent} />
                        <Route path="/users" component={ListUserComponent} />
                        <Route path="/add-user" component={AddUserComponent} />
                        <Route path="/edit-user" component={EditUserComponent} />
                    </Switch>
                </div>
            </Router>
        </div>
    )
}

export default AppRouter;