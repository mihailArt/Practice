import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';

class PurchaseEdit extends Component {

  emptyItem = {
    product: null,
    client: null,
    data: null,
    cost: 0,
    number: 0
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
      const purchase = await (await fetch(`/purchases/${this.props.match.params.id}`)).json();
      this.setState({item: purchase});
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
        await fetch(`/purchases/${item.id}`, {
          method: 'PUT' ,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
    }
    else{
        await fetch('/purchases', {
          method: 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
    }
    this.props.history.push('/purchases');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit purchase' : 'Add purchase'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
              <Label for="product">Product</Label>
              <Input type="text" name="product" id="product" value={item.product.id || ''}
                    onChange={this.handleChange} autoComplete="product"/>
          </FormGroup>
          <FormGroup>
              <Label for="client">Client</Label>
              <Input type="text" name="client" id="client" value={item.client.id || ''}
                     onChange={this.handleChange} autoComplete="client"/>
           </FormGroup>
          <FormGroup>
              <Label for="data">Data</Label>
              <Input type="text" name="data" id="data" value={item.data || ''}
                     onChange={this.handleChange} autoComplete="data"/>
          </FormGroup>
          <FormGroup>
              <Label for="cost">Cost</Label>
              <Input type="text" name="cost" id="cost" value={item.cost || ''}
                     onChange={this.handleChange} autoComplete="cost"/>
          </FormGroup>
          <FormGroup>
          <FormGroup>
              <Label for="number">Number</Label>
              <Input type="text" name="number" id="number" value={item.number || ''}
                     onChange={this.handleChange} autoComplete="number"/>
          </FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/clients">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(PurchaseEdit);