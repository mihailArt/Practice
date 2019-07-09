import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class ProductList extends Component {

  constructor(props) {
    super(props);
    this.state = {products: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('products')
      .then(response => response.json())
      .then(data => this.setState({products: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/products/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedProducts = [...this.state.products].filter(i => i.id !== id);
      this.setState({products: updatedProducts});
    });
  }

  render() {
    const {products, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const productList = products.map(product => {
      const cost = `${product.cost || ''}`;
      const category = `${product.category || ''}`;
      return <tr key={product.id}>
        <td style={{whiteSpace: 'nowrap'}}>{product.nameProduct}</td>
        <td>{cost}</td>
        <td>{category}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/products/" + product.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(product.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/products/new">Add product</Button>
          </div>
          <h3>Products</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="30%">Name</th>
              <th width="30%">Cost</th>
              <th width="30%">Category</th>
            </tr>
            </thead>
            <tbody>
            {productList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default ProductList;