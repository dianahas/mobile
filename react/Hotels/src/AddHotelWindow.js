import React from 'react';
import {StyleSheet, Button, Linking, TextInput, View,Alert} from 'react-native';

export default class AddHotelWindow extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            id: 0,
            name: "",
            location: ""
        };

        if (this.props.navigation.state.params.id !== undefined) {
            let currentHotel = this.props.navigation.state.params;
            this.state.id = currentHotel.id;
            this.state.name = currentHotel.name;
            this.state.location = currentHotel.location;
        }

    }

    save() {
        let hotel = {};
        if (this.state.id === 0) {
            hotel = {
                id: global.count,
                name: this.state.name,
                location: this.state.location,
            };
            global.count = global.count + 1;
            global.hotels.push(hotel);
        }
        else {
            hotel = this.state;
            for (let i = 0; i < global.hotels.length; i++) {
                if (global.hotels[i].id === hotel.id) {
                    global.hotels[i] = hotel;
                }
            }
        }
        this.props.navigation.navigate("Home");
        Linking.openURL("mailto:hatmanudiana@gmail.com?subject=HotelsApp&body=" + JSON.stringify(hotel));
    }

    render() {
        return (
            <View style={styles.container}>
                <TextInput style={styles.defaultTextInput}
                           onChangeText={(name) => this.setState({name})}
                           value={this.state.name.toString()}/>
                <TextInput style={styles.defaultTextInput}
                           onChangeText={(location) => this.setState({location})}
                           value={this.state.location}/>
                <Button title="save" onPress={() => this.save()}/>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    navbar: {
        flex:1,
        flexDirection:"row",
        justifyContent: "space-around"
    },
    container: { paddingVertical: 60 },
    titleText: { fontSize:48 },
    defaultTextInput: { height:40 },
    defaultMultiLine: {
        height: 250,
        textAlignVertical: 'top'
    },
    defaultButton: {}
});