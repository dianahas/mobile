import {AsyncStorage} from "react-native";
import React from "react";

export class UserRepository {
    constructor() {
        this.users = {listOfUsers: [], max_id: 0};
        this.updateList().done();
    }

    async updateList() {
        try {
            let response = await AsyncStorage.getItem("listOfUsers");

            let parsed = await JSON.parse(response) || {listOfUsers: [], max_id: 0};
            this.data = parsed;
        }
        catch (error) {
            console.log(error);
        }
    }

    async handleAddUser(user) {
        user.id = this.users.max_id;
        let listOfUsers = [...this.users.listOfUsers, {user: user}];
        let max_id = this.users.max_id + 1;

        console.log("data: ", this.users.listOfUsers);
        console.log("local: ", listOfUsers);

        await AsyncStorage.setItem("listOfUsers", JSON.stringify({listOfUsers: listOfUsers, max_id: max_id}));
        this.updateList().done();
        console.log("Ending");
    }
}