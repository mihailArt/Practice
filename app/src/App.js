import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from './Product/ProductList';
import ProductEdit from './Product/ProductEdit';
import ClientList from './Client/ClientList';
import ClientEdit from './Client/ClientEdit';
import PurchaseList from './Purchase/PurchaseList';
import PurchaseEdit from './Purchase/PurchaseEdit';
import AppNavbar from './AppNavbar';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={AppNavbar}/>
          <Route path='/products' exact={true} component={ProductList}/>
          <Route path='/products/:id' component={ProductEdit}/>
          <Route path='/clients' exact={true} component={ClientList}/>
          <Route path='/clients/:id' component={ClientEdit}/>
          <Route path='/purchases' exact={true} component={PurchaseList}/>
          <Route path='/purchases/:id' component={PurchaseEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;