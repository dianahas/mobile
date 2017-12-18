import React from 'react';
import {StyleSheet, Button, Linking, TextInput, View, Alert, Picker} from 'react-native';
import {HotelsRepository} from "./HotelsRepository";

export default class AddHotelWindow extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            id: 0,
            name: "",
            location: ""
        };

        this.repo = new HotelsRepository();

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
            <View style={styles.myView}>
                <TextInput style={styles.inputText}
                           onChangeText={(name) => this.setState({name})}
                           value={this.state.name.toString()}/>
                <Picker
                    selectedValue={this.state.location}
                    onValueChange={(itemValue, itemIndex) => this.setState({location: itemValue})}>
                    <Picker.Item label="Italy" value="Italy" />
                    <Picker.Item label="Germany" value="Germany" />
                    <Picker.Item label="USA" value="USA" />
                    <Picker.Item label="Romania" value="Romania" />
                </Picker>

                <View style={styles.myButton}>
                    <Button title="save" onPress={() => this.save()}/>
                </View>

            </View>
        );
    }
}

const styles = StyleSheet.create({
    myView: {
        height: 600,
        width: 400,
        backgroundColor: '#993366'
    },
    myText: {
        marginTop: 30,
        fontSize: 60,
        textAlign: 'center',
        color: '#ffffcc'
    },
    inputText: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
    inputText1: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
    myButton: {
        height: 60,
        width: 100,
        marginTop: 20,
        marginLeft: 140,
        backgroundColor: '#ffffcc',
        borderRadius: 20,
        padding: 10,
        shadowOffset: {
            width: 0,
            height: 3
        },
        shadowRadius: 10,
        shadowOpacity: 0.25
    }
});