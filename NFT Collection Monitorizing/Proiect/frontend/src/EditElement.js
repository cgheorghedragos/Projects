import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditElement() {
    const [myId, setId] = useState('')
    const [floorPrice, setFloorPrice] = useState('');
    const [totalSupply, setTotalSupply] = useState('');
    const [numOwners, setNumOwners] = useState('');
    const [name, setName] = useState('');
    const [totalVolume, setTotalVolume] = useState('');

    const params = useParams();

    const navigate = useNavigate();
    const getId = params.id;

    const handleClick = () => {
        navigate("/");
    }

    const handleEdit = () => {
        const data = {
           
        };
        data._id = myId;
        data.floorPrice = floorPrice;
        data.totalSupply = totalSupply;
        data.totalVolume = totalVolume;
        data.name = name;
        data.numOwners = numOwners;


        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        fetch('http://localhost:4000/api/collections/update-nft', requestOptions)
            .then(response => response.json())
            .then(data => {
                alert('Updated');
            });
    }

    useEffect(() => {
        fetch("http://localhost:4000/api/collections/get/" + getId)
            .then(res => res.json())
            .then(data => {
                setId(data._id);
                setFloorPrice(data.floorPrice);
                setTotalSupply(data.totalSupply);
                setNumOwners(data.numOwners);
                setName(data.name);
                setTotalVolume(data.totalVolume);
            })
            .catch(err => console.log(err))

           
    }, [])

    return (
        <div className="containerEditText">
            
            <label className="labelEdit" disable="true" >Floor Price:</label>
            <input className="inputClass" type="text" value={floorPrice} onChange={(event) => {setFloorPrice(event.target.value);}}></input>
            
            <label >Total Supply:</label>
            <input className="inputClass" type="text" value={totalSupply} onChange={(event) => {setTotalSupply(event.target.value);}}></input>

            <label>Num Oweners:</label>
            <input className="inputClass" type="text" value={numOwners} onChange={(event) => {setNumOwners(event.target.value);}}></input>

            <label>Name :</label>
            <input className="inputClass" type="text" value={name} onChange={(event) => {setName(event.target.value);}}></input>

            <label>Total Volume:</label>
            <input className="inputClass" type="text" value={totalVolume} onChange={(event) => {setTotalVolume(event.target.value);}}></input>

            <button className="buttonGoBack" onClick={handleClick}>Go Back</button>
            <button className="buttonEdit" onClick={handleEdit}>Save Data</button>
        

        </div>
    )
}

export default EditElement;