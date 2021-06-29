import React, {useEffect, useState} from 'react';
import axios from "axios";

const Questions = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQ, setCurrentQ] = useState();

    useEffect(() => {
        axios.get("http://localhost:80/question/getAll", {
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        }).then(value => {
            console.log(value)
            setQuestions(value.data.questionList)
            console.log(questions)
        })
    }, [])

    const search = (event) => {
        let id = event.target.value;
        axios.get("http://localhost:80/question/" + id).then(value => {
            console.log(value)
            setCurrentQ(value.data)
            setQuestions([]);
        })
    }

    return (
        <div>
            <h1>Barcha savollar</h1>
            {/*//bitta search*/}
            <input onChange={search} placeholder="Search"/>
            {
                questions.length > 0 ? questions.map((value, index) => {
                    return (<div>
                        <div className="card">
                            <div className="card-body">
                                <h4>{value.questionID}</h4>
                                <h3>{value.questionTitle}</h3>
                                <h6>{value.questionText}</h6>
                                <h6>{value.owner}</h6>
                            </div>
                        </div>
                    </div>)
                }) : currentQ ? <div>
                    <div className="card">
                        <div className="card-body">
                            <h4>{currentQ.questionID}</h4>
                            <h3>{currentQ.questionTitle}</h3>
                            <h6>{currentQ.questionText}</h6>
                            <h6>{currentQ.owner}</h6>
                        </div>
                    </div>
                </div> : "Ma'lumot topilmadi!"
            }
        </div>
    );
};

export default Questions;