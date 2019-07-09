import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class PurchaseList extends Component {

  constructor(props) {
    super(props);
    this.state = {purchases: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('purchases')
      .then(response => response.json())
      .then(data => this.setState({purchases: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/purchases/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedPurchases = [...this.state.Purchases].filter(i => i.id !== id);
      this.setState({purchases: updatedPurchases});
    });
  }

  render() {
    const {purchases, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const purchaseList = purchases.map(purchase => {
      const product = `${purchase.product.nameProduct || ''}`;
      const client = `${purchase.client.clientName || ''}`;
      const data = `${purchase.data || ''}`;
      const cost = `${purchase.cost || ''}`;
      const number = `${purchase.number || ''}`;
      return <tr key={purchase.id}>
        <td>{product}</td>
        <td>{client}</td>
        <td>{data}</td>
        <td>{cost}</td>
        <td>{number}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/purchases/" + purchase.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(purchase.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/purchases/new">Add purchase</Button>
          </div>
          <h3>Purchases</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="10%">product</th>
              <th width="10%">client</th>
              <th width="20%">data</th>
              <th width="20%">cost</th>
              <th width="20%">number</th>
            </tr>
            </thead>
            <tbody>
            {purchaseList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default PurchaseList;