import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';

class ProductEdit extends Component {

  emptyItem = {
    nameProduct: '',
    cost: '',
    category: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const product = await (await fetch(`/products/${this.props.match.params.id}`)).json();
      this.setState({item: product});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    if(item.id){
        await fetch(`/products/${item.id}`, {
          method: 'PUT' ,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
    }
    else{
        await fetch('/products', {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
    }
    this.props.history.push('/products');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Product' : 'Add Product'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="nameProduct">Name</Label>
            <Input type="text" name="nameProduct" id="nameProduct" value={item.nameProduct || ''}
                   onChange={this.handleChange} autoComplete="nameProduct"/>
          </FormGroup>
          <FormGroup>
            <Label for="cost">Cost</Label>
            <Input type="text" name="cost" id="cost" value={item.cost || ''}
                   onChange={this.handleChange} autoComplete="cost"/>
          </FormGroup>
          <FormGroup>
            <Label for="category">Category</Label>
            <Input type="text" name="category" id="category" value={item.category || ''}
                   onChange={this.handleChange} autoComplete="category"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/products">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(ProductEdit);