import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import deleteIcon from "../../src/resources/trash-2 (1).svg";
import '../App.css'

const Train = props => (
  <tr>
    <td> {props.train.trainid} </td>
    <td> {props.train.trainName} </td>
    <td> {props.train.startStation} </td>
    <td> {props.train.endStation} </td>
  </tr>


);
class TrainList extends Component {
  constructor(props) {
    super(props);
    this.state = { traintickets: [] };
  }

  //Get the train ticket details from the database
  componentDidMount() {
    axios
      .get("http://localhost:9030/search/findAllTrains")
      .then(response => {
        this.setState({ traintickets: response.data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  trainList() {
    return this.state.traintickets.map(function (currentTrain, i) {
      return <Train train={currentTrain} key={i} />;
    });
  }



  render() {
    return (
      <div>


        <h3 style={{
          fontFamily: 'Poppins',
          padding: 20

        }}> <center>Trains Available </center></h3>
        <table className="table table-striped" style={{ marginTop: 20 }}>
          <thead>
            <tr >

              <th> TrainID </th>
              <th> Train Name </th>
              <th> Seats Available </th>
              <th> Source </th>
              <th> Destination </th>
            </tr>
          </thead>
          <tbody>{this.trainList()}</tbody>
        </table>
      </div>
    );
  }
}

export default TrainList;
