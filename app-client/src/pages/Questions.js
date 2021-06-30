import React, {useEffect, useState} from 'react';
import axios from "axios";
import {AvField, AvForm} from "availity-reactstrap-validation";
import {Button} from "reactstrap";

const Questions = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQ, setCurrentQ] = useState();
    const [oneQuestionComments, setOneQuestionComments] = useState([]);
    const [currentQID, setCurrentQID] = useState('')

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

    const getMyComments = (str) => {
        console.log(str)
        axios.get("http://localhost:80/question/" + str, {
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        }).then(value => {
            console.log(value);
            setOneQuestionComments(value.data.commentList)
        })
    }
    const search = (event) => {
        let id = event.target.value;
        console.log(id !== '')
        if (id !== '') {
            axios.get("http://localhost:80/question/" + id).then(value => {
                console.log(value)
                setCurrentQ(value.data)
                setQuestions([]);
            })
            getMyComments(id);
        }
        else {
            axios.get("http://localhost:80/question/getAll", {
                headers: {
                    'Authorization': localStorage.getItem("token")
                }
            }).then(value => {
                setQuestions(value.data.questionList)
            })
        }
    }

    const saveComment = (event, values) => {
        console.log(currentQID)
        axios.post("http://localhost:80/comment", {
            textComment: values.text,
            questionId: currentQID
        }).then(value => {
            console.log(value);
        })
    }

    return (
        <div>
            <div className="container">
                <h1>Barcha savollar</h1>
                {/*//bitta search*/}
                <input onChange={search} placeholder="Search"/>
                {
                    questions.length > 0 ? questions.map((value, index) => {
                        return (<div>
                            <div className="card">
                                <div className="card-title">
                                    <h3>{value.questionTitle}</h3>
                                </div>
                                <div className="card-body">
                                    <h4>{value.questionID}</h4>
                                    <h6>{value.questionText}</h6>
                                    <h6>{value.owner}</h6>
                                </div>
                                <div className="card-footer">

                                    {oneQuestionComments.map((value1, index1) => {
                                        return (<div>
                                            <h6>{value1.text}</h6>
                                        </div>)
                                    })}
                                    <p>Izoh un joy </p>
                                    <AvForm onValidSubmit={saveComment}>
                                        {/* With AvField */}
                                        <AvField name="text" label="Comment Body" required/>
                                        <Button type="submit" onClick={
                                            () => setCurrentQID(value.questionID)
                                        }>Submit</Button>
                                    </AvForm>
                                </div>
                            </div>
                        </div>)
                    }) : currentQ ? <div>
                        <div className="card">
                            <div className="card-title">
                                <h3>{currentQ.questionTitle}</h3>
                            </div>
                            <div className="card-body">
                                <h4>{currentQ.questionID}</h4>
                                <h6>{currentQ.questionText}</h6>
                                <h6>{currentQ.owner}</h6>
                            </div>

                            <div className="card-footer">

                                {oneQuestionComments.map((value1, index1) => {
                                    return (<div>
                                        <h6>{value1.text}</h6>
                                    </div>)
                                })}

                                <p>Izoh un joy </p>
                                <AvForm onValidSubmit={saveComment}>
                                    {/* With AvField */}
                                    <AvField name="text" label="Comment Body" required/>
                                    <Button type="submit" onClick={
                                        () => setCurrentQID(currentQ.questionID)
                                    }>Submit</Button>
                                </AvForm>
                            </div>
                        </div>
                    </div> : "Ma'lumot topilmadi!"
                }

            </div>
        </div>
    );
};

export default Questions;