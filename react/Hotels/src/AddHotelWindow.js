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

        this.repo=props.navigation.state.params.repo;

        // if (this.props.navigation.state.params.id !== undefined) {
        //     let currentHotel = this.props.navigation.state.params;
        //     this.state.id = currentHotel.id;
        //     this.state.name = currentHotel.name;
        //     this.state.location = currentHotel.location;
        // }

    }

    save() {
        if (this.state.id === 0) {
            this.repo.handleAddHotel(this.state);
            console.log("After add" + JSON.stringify(this.repo.data));
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
        Linking.openURL("mailto:hatmanudiana@gmail.com?subject=HotelsApp&body=" + JSON.stringify(this.state));
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