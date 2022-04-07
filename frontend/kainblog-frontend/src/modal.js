import React from "react";
import styles from "./modal.css";
import { RiCloseLine } from "react-icons/ri";

const Modal = ({ setIsOpen, event }) => {
    let message = "";
    let error = "";
    switch (event) {
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
    }

  return (
    <>
      <div className={styles.darkBG} onClick={() => setIsOpen(false)} />
      <div className={styles.centered}>
        <div className={styles.modal}>
          <div className={styles.modalHeader}>
            <h5 className={styles.heading}>
                {message != "" ? "Nachricht" : error != "" ? "Fehler" : ""}</h5>
          </div>
          <button className={styles.closeBtn} onClick={() => setIsOpen(false)}>
            <RiCloseLine style={{ marginBottom: "-3px" }} />
          </button>
          <div className={styles.modalContent}>
          {message != "" ? error : error != "" ? error : ""}
          </div>
          <div className={styles.modalActions}>
            <div className={styles.actionsContainer}>
              <button className={styles.deleteBtn} onClick={() => setIsOpen(false)}>
                Delete
              </button>
              <button
                className={styles.cancelBtn}
                onClick={() => setIsOpen(false)}
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