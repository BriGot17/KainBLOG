import {Route, Link, Routes, useParams} from 'react-router-dom';
import ArticleList from './articleList';
import './Profile.css'

function Profile() {

    const params = useParams();

    return (
        <div class="profilecontainer">
            <h2>Profil</h2>
            <br/>
            <table class="profiletable">
                <tr>
                    <td class="headers">Username</td>
                    <td class="items" name="username">gotped17</td>
                </tr>
                <tr>
                    <td class="headers">Name</td>
                    <td class="items" name="name">Peter Wolfgang Gottlieb</td>
                </tr>
                <tr>
                    <td class="headers">Jahrgang</td>
                    <td class="items" name="year">DHIF17</td>
                </tr>
                <tr>
                    <td class="headers">Rolle</td>
                    <td class="items" name="role">Admin</td>
                </tr>
            </table>
            <br/>
            <div>
                <h3>Profilbeschreibung</h3>
                <p class="description">Die Profilbeschreibung des Users.</p>
            </div>
            <ArticleList/>
        </div>
    );

}

export default Profile;