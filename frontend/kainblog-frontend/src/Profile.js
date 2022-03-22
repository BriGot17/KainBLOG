function Profile() {

    return (
        <div>
            <h2>Profile</h2>
            <br/>
            <table>
                <tr>
                    <td>Username</td>
                    <td name="username"></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td name="name"></td>
                </tr>
                <tr>
                    <td>Jahrgang</td>
                    <td name="year"></td>
                </tr>
                <tr>
                    <td>Rolle</td>
                    <td name="role"></td>
                </tr>
            </table>
            <br/>
            <label>Profilbeschreibung</label>
        </div>
    );

}

export default Profile;