export class Hotel {

    constructor(name) {
        this.hotelId = 0;
        this.name = name;
    }

    constructor() {}


    async getAllHotels() {
        const hotelMock = new Hotel("alala");
        const hotelMock2 = new Hotel("alala2");

        let list = [];
        list.push(hotelMock);
        list.push(hotelMock2);

        return list
    }
}