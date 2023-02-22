import {  useNavigate } from "react-router";

function CollectionElement(props) {
    const navigate = useNavigate();

    function deleteItem(item){
        const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(item)
        };
        fetch('http://localhost:4000/api/collections/delete-nft', requestOptions)
            .then(response => response.json())
            .then(data => {
                alert('deleted');
                window.location.reload();
            });
    }

    function updateClick(item){
            navigate("/edit/"+item._id);
    }

    return (
        <div className="card">
        <div className="cardInterior">
            <div className="cardProprieties">
                <p>Id: {props._id}</p>
                <p>Floor Price: {props.floorPrice}</p>
                <p>Total Supply: {props.totalSupply}</p>
                <p>Num Owners: {props.numOwners}</p>
                <p>Name: {props.name}</p>
                <p>Total Volume: {props.totalVolume}</p>
            </div>
            
           <div className="buttonsDiv">
            <label className="labelC"> Change it:
            </label>
           <button className="updateButton" onClick={() => updateClick(props)}><img src="/pen.png" alt="update" border="0"/></button>
            <button className="deleteButton" onClick={() => deleteItem(props)}><img src="/trash.png" alt="delete" border="0"/></button>
           </div>
        </div>
        </div>
    )
}

export default CollectionElement;