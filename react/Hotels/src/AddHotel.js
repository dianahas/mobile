import React from 'react';
import {Button, Linking, TextInput, View} from 'react-native';

export default class AddHotelWindow extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            id: 0,
            name: "",
            location: ""
        };

        if (this.props.navigation.state.params.id !== undefined) {
            var toEdit = this.props.navigation.state.params;
            this.state.id = toEdit.id;
            this.state.name = toEdit.name;
            this.state.location = toEdit.location;
        }

    }

    save() {
        if (this.state.id === 0) {
            var hotel = {
                id: global.count,
                name: this.state.name,
                location: this.state.location,
            };
            global.count = global.count + 1;
            global.hotels.push(item);
        }
        else {
            var hotel = this.state;
            for (let i = 0; i < global.hotels.length; i++) {
                if (global.hotels[i].id === hotel.id) {
                    global.hotels[i] = hotel;
                }
            }
        }
        Linking.openURL("mailto:hatmanudiana@gmail.com?subject=HotelsApp&body=" + JSON.stringify(hotel));
        this.props.navigation.navigate("Home");
    }

    render() {
        return (
            <View>
                <TextInput onChangeText={(name) => this.setState({name})} value={this.state.name}/>
                <TextInput onChangeText={(location) => this.setState({location})} value={this.state.location}/>
                <Button title="save" onPress={() => this.save()}/>
            </View>
        );
    }

}