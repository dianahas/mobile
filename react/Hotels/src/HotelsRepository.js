import {AsyncStorage} from "react-native";
import React from "react";

export class HotelsRepository extends React.Component {
    constructor(props) {
        super(props);
        this.data = {listOfHotels: [], max_id: 0};
        this.updateList().done();
        console.log("At construct" + JSON.stringify(this.data));
    }

    async updateList()
    {
        try
        {
            let response = await AsyncStorage.getItem("listOfHotels");

            let parsed = await JSON.parse(response) || {listOfHotels: [], max_id: 0};
            this.data = parsed;
            console.log("Update " + JSON.stringify(this.data));
        }
        catch(error)
        {
            console.log(error);
        }
    }


    async handleAddHotel(hotel)
    {
        hotel.id=this.data.max_id;
        let listOfHotels = [...this.data.listOfHotels, {hotel: hotel}];
        let max_id = this.data.max_id + 1;

        console.log("data: ", this.data.listOfHotels);
        console.log("local: ", listOfHotels);

        await AsyncStorage.setItem("listOfHotels", JSON.stringify({listOfHotels: listOfHotels, max_id: max_id}));
        this.updateList().done();
        console.log("Ending");
    }


    // async handleChangedObject(receipt)
    // {
    //     let listOfHotels = this.data.listOfHotels;
    //     console.log("PUKA",listOfHotels);
    //     for (let i = 0; i < listOfHotels.length; i++) {
    //         if (listOfHotels[i].recipe.id === receipt.id) {
    //             console.log("PUKA");
    //             listOfHotels[i].recipe=receipt
    //         }
    //     }
    //     await AsyncStorage.setItem("listOfRecipes", JSON.stringify({listOfRecipes: listOfRecipes, max_id: this.data.max_id}));
    //     this.updateList().done();
    // }
    //
    //
    // async handleClickedDelete(receipt)
    // {
    //     //console.log("Delete: ", index);
    //
    //     let listOfRecipes = this.data.listOfRecipes;
    //     let index=-1;
    //     for (let i = 0; i < listOfRecipes.length; i++) {
    //         if (listOfRecipes[i].recipe.id === receipt.id) {
    //             console.log("PUKA");
    //             index=i;
    //         }
    //     }
    //     listOfRecipes.splice(index, 1);
    //
    //     await AsyncStorage.setItem("listOfRecipes", JSON.stringify({listOfRecipes: listOfRecipes, max_id: this.data.max_id}));
    //
    //     this.updateList().done()
    // }
}