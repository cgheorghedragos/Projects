import {useEffect, useState } from 'react'
import CollectionElement from './CollectionElement'

function CollectionsList(){
    const [list, setList] = useState(([]))

    useEffect( () => {
        fetch("http://localhost:4000/api/collections/get-nft-collection")
        .then(res => res.json())
        .then ( data => setList(data))
        .catch ( err => console.log(err))
    }, [] )

    return (
        <div className="cardParent">
            {list.slice(0,20).map((element) => (
        
                <CollectionElement {...element} key = {element._id}/>
            
            ))}
        </div>
    )
}

export default CollectionsList;