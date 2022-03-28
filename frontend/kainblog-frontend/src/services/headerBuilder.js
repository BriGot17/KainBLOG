
class HeaderBuilder {

    buildEssentialHeaders(){
        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        const user = JSON.parse(localStorage.getItem('user'));
        if(csrfToken && user && user.token){
            return {
                'Authorization': `Bearer ${user.token}`,
                'X-XSRF-TOKEN': csrfToken
            }
        }else{
            return {};
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