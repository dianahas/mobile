import React from 'react';
import {ListView, StyleSheet, Text, TouchableOpacity, View} from 'react-native';

export default class HotelsList extends React.Component {
    constructor(props) {
        super(props);

        this.repo = props.navigation.state.params.repo;
        let dataSource = new ListView.DataSource({rowHasChanged: (r1, r2) => r1.Id !== r2.Id});

        this.state = {
            dataSource: dataSource.cloneWithRows(this.repo.data.listOfHotels)
        };
    }

    edit(selected) {
        this.props.navigation.navigate("ManageHotelWindow", {selected: selected, repo: this.repo});
    }

    renderRow(selected) {
        console.log(selected);
        return (
            <TouchableOpacity onPress={() => this.edit(selected)}>
                <View>
                    <Text style={styles.listItem}>{selected.hotel.name}</Text>
                </View>
            </TouchableOpacity>
        );
    }

    render() {
        return (
            <View style={styles.myView}>
                <Text style={styles.myText}>Hotels</Text>
                <ListView style={styles.myList}
                          dataSource={this.state.dataSource}
                          renderRow={this.renderRow.bind(this)}/>
            </View>

        );
    }

};

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
    myList: {
        marginTop: 50
    },
    listItem: {
        marginTop: 10,
        textAlign: 'center',
        fontSize: 20,
        color: '#ffffcc'
    }
});