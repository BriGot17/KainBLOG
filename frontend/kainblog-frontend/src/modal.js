import React from "react";
import "./modal.css";
import { RiCloseLine } from "react-icons/ri";

const Modal = ( props) => {
    let message = "";

    let error = "";
    switch (props.status) {
      
        case "not authenticated":
            error = "Your session has ended";
            break;
        case "new success":
            message = "Erstellen erfolgreicht";
            break;
        case "edit success":
            message = "Editieren erfolgreich";
            break;
        case "server error":
            error = "Ein Problem auf dem Server behindert diesen Service";
            break;
        case "login success":
            message = "Sie sind angemeldet";
            break;
        case "new error":
            error = "Fehler beim Erstellen";
            break;
        case "edit error":
            error = "Fehler beim Bearbeiten";
            break;
        default:
            message = "Das ist ein Modal für etwaige Nachrichten, die der Nutzer bekommen soll";
            break;
    }

  return (
    <>
      <div className="darkBG" onClick={() => props.setIsOpen(false)} />
      <div className="centered">
        <div className="modal">
          <div className="modalHeader">
            <h5 className="heading">
                {message !== "" ? "Nachricht" : error !== "" ? "Fehler" : ""}</h5>
          </div>
          <button className="closeBtn" onClick={() => props.setIsOpen(false)}>
            <RiCloseLine style={{ marginBottom: "-3px" }} />
          </button>
          <div className="modalContent">
          {message !== "" ? message : error !== "" ? error : ""}
          </div>
          <div className="modalActions">
            <div className="actionsContainer">
              <button
                className="cancelBtn"
                onClick={() => props.setIsOpen(false)}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div>
     </>
  );
};

export default Modal;