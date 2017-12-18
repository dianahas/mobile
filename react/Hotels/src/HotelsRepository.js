import {AsyncStorage} from "react-native";
import React from "react";

export class HotelsRepository {
    constructor() {
        this.data = {listOfHotels: [], max_id: 0};
        this.updateList().done();
    }

    async updateList() {
        try {
            let response = await AsyncStorage.getItem("listOfHotels");

            let parsed = await JSON.parse(response) || {listOfHotels: [], max_id: 0};
            this.data = parsed;
        }
        catch (error) {
            console.log(error);
        }
    }

    async handleAddHotel(hotel) {
        hotel.id = this.data.max_id;
        let listOfHotels = [...this.data.listOfHotels, {hotel: hotel}];
        let max_id = this.data.max_id + 1;

        console.log("data: ", this.data.listOfHotels);
        console.log("local: ", listOfHotels);

        await AsyncStorage.setItem("listOfHotels", JSON.stringify({listOfHotels: listOfHotels, max_id: max_id}));
        this.updateList().done();
        console.log("Ending");
    }


    async handleChangedObject(selected) {
        let listOfHotels = this.data.listOfHotels;
        for (let i = 0; i < listOfHotels.length; i++) {
            if (listOfHotels[i].hotel.id === selected.id) {
                listOfHotels[i].hotel = selected;
            }
        }
        await AsyncStorage.setItem("listOfHotels", JSON.stringify({
            listOfHotels: listOfHotels,
            max_id: this.data.max_id
        }));
        this.updateList().done();
    }


    async handleClickedDelete(selected) {
        let listOfHotels = this.data.listOfHotels;
        let index = -1;
        for (let i = 0; i < listOfHotels.length; i++) {
            if (listOfHotels[i].hotel.id === selected.id) {
                index = i;
            }
        }
        listOfHotels.splice(index, 1);

        await AsyncStorage.setItem("listOfHotels", JSON.stringify({
            listOfHotels: listOfHotels,
            max_id: this.data.max_id
        }));

        this.updateList().done()
    }
}