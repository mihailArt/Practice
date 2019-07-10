import React, { Component } from 'react';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {isOpen: false};
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {
    return <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/products">Products</NavbarBrand>
      <NavbarBrand tag={Link} to="/clients">Clients</NavbarBrand>
      <NavbarBrand tag={Link} to="/purchases">Purchases</NavbarBrand>
      <NavbarToggler onClick={this.toggle}/>
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink href="https://github.com/mihailArt/Practice">GitHub</NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>;
  }
}