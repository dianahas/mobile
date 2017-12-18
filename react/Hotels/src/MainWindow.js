import React from 'react';
import {Button, ListView, StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import {HotelsRepository} from "./HotelsRepository";

export default class MainWindow extends React.Component {
    constructor(props) {
        super(props);

        this.repo = new HotelsRepository();
        console.log("After Home constr" + JSON.stringify(this.repo.data));
        let dataSource = new ListView.DataSource({rowHasChanged: (r1, r2) => r1.Id !== r2.Id});


        this.state = {
            dataSource: dataSource.cloneWithRows(this.repo.data.listOfHotels)
        };
        console.log("Data source" + JSON.stringify(this.state));
    }

    edit(hotel) {
        this.props.navigation.navigate("AddHotelWindow", hotel);
    }

    renderRow(hotel) {
        return (
            <TouchableOpacity onPress={() => this.edit(hotel)}>
                <View>
                    <Text>{hotel.name}</Text>
                </View>
            </TouchableOpacity>
        );
    }


    add() {
        this.props.navigation.navigate("AddHotelWindow", {repo:this.repo});
    }

    render() {
        return (
            <View>
                <Text>Hotels</Text>
                <ListView dataSource={this.state.dataSource}
                          renderRow={this.renderRow.bind(this)}/>
                <Button title="Add" onPress={() => this.add()}/>
            </View>
        );
    }

};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});