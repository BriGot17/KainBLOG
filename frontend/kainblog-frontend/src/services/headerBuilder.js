
class HeaderBuilder {

    buildArticleheader(){
        const user = JSON.parse(localStorage.getItem('user'));
        if(user.token){
            return {
                'Authorization': `Bearer ${user.token}`,
                'Content-Type': "application/json"
            }
        }
    }


    buildAuthHeader(){
        
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.token) {
          return {
              'Authorization': `Bearer ${user.token}`
          } 
          
        } else {
          return {};
        }
    }
}