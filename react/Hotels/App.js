import {Hotel} from "./src/controllers/Hotel";
import * as React from "react";
import {
Card,
CardTitle,
CardText,
CardActions,
Button,
CardMenu,
IconButton,
List,
ListItem} from 'react-mdl'

export default class Hotels extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hotels: []
        };
        this.hotel = new Hotel();
        this.renderHotel = this.renderHotel.bind(this)
    }

    async componentDidMount() {
        //fetch jobs from firebase and put them on state to acces when rendering
        this.setState({
            hotels: await this.hotel.getAllHotels()
        })
    }

    renderHotel(key, hotel) {
        console.log("renderHotel", hotel, key);
        return (
            <ListItem key={key}>
                <Card shadow={0} style={{width: '512px', margin: 'auto'}}>
                    <CardTitle style={{height: '100px'}}>{hotel.name}</CardTitle>
                    <CardText>
                        {job.body}
                    </CardText>
                    <CardActions border>
                        <Button colored>See</Button>
                    </CardActions>
                    <CardMenu style={{color: '#fff'}}>
                        <IconButton name="share"/>
                    </CardMenu>
                </Card>
            </ListItem>
        )
    }
}

