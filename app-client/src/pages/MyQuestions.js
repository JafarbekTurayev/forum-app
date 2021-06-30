import React, {useEffect, useState} from 'react';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
import {AvField, AvForm} from "availity-reactstrap-validation";
import axios from "axios";

const MyQuestions = () => {
    const [openModal, setOpenModal] = useState(false);
    const [myQuestions, setMyQuestions] = useState([]);

    const getMyQuestions = () => {
        axios.get("http://localhost:80/question/getMyAll", {
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        }).then(value => {
            setMyQuestions(value.data.questionList)
            console.log(myQuestions)
        })
    }


    useEffect(() => {
        getMyQuestions();
    }, [])
    const toggle = () => setOpenModal(!openModal);
    const save = (event, values) => {
        console.log("keldi!")
        console.log(localStorage.getItem("token"))
        axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
        axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
        axios.post("http://localhost:80/question", values, {
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        }).then(value => {
            console.log(value);
            getMyQuestions();
        })
    }
    return (
        <div>
            <div className="container">
                <h1>Mening savollarim</h1>
                <Button onClick={toggle}>Savol qo'shish</Button>

                <Modal isOpen={openModal} toggle={toggle}>
                    <ModalHeader toggle={toggle}>Savol qo'shish</ModalHeader>
                    <AvForm onValidSubmit={save}>
                        {/* With AvField */}
                        <ModalBody>
                            <AvField name="title" label="Title" required/>
                            <AvField name="text" label="Text" required/>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="success" onClick={toggle} type="submit">Save</Button>
                            <Button color="danger" onClick={toggle}>Cancel</Button>
                        </ModalFooter>
                    </AvForm>
                </Modal>

                {
                    myQuestions ? myQuestions.map((value, index) => {
                        return (<div>
                            <div className="card">
                                <div className="card-body">
                                    <h4>{value.questionID}</h4>
                                    <h3>{value.questionTitle}</h3>
                                    <h6>{value.questionText}</h6>
                                </div>
                            </div>
                        </div>)
                    }) : "Ma'lumot topilmadi!"
                }
                <div className=" ">
                    <Table className={'table table-bordered'}>
                        <tr>

                        </tr>

                    </Table>
                </div>
            </div>
        </div>
    );
};

export default MyQuestions;