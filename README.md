# 2018_1_Technokek
Игра про моделирование системы метрополитена и трамвайной сети. Аналогичная игра - Mini Metro.

# [Работающее хероку приложение](https://technokek2018.herokuapp.com/)

## Current API for last commit:

### GET USER:           
Url: `/user/<id>/`
```
    {
        message: {
            id: 1,
            nickname: "vitaliycherkov",
            score: 0,
            email: "vitaliycherkov@gmail.com",
            games_number: 0,
            avatar: null,
        },
        successful: true,
    }
```

### GET ME:
Url: `/user/me`
```

    if your id attribute === user ID -> you can get this data

    {
        message: {
            id: 1,
            nickname: "vitaliycherkov",
            password: 1,
            email: "vitaliycherkov@gmail.com",
            avatar: null,
        },
        successful: true,
    }
```

#### GET SCOREBOARD:     
Url: `/scoreboard/`
```
    {
        message: {
            another: [
                {
                    id: 2,
                    nickname: "vladbusov",
                    score: 0,
                    email: "vladbusov@gmail.com",
                    games_number: 0,
                    avatar: null,
                },
                {
                    id: 1,
                    nickname: "vitaliycherkov",
                    score: 0,
                    email: "vitaliycherkov@gmail.com",
                    games_number: 0,
                    avatar: null,
                },
                {
                    id: 0,
                    nickname: "ivansport",
                    score: 0,
                    email: "ivansport@gmail.com",
                    games_number: 0,
                    avatar: null,
                },
            ],
            me: null,
        },
        successful: true,
    }
```


### POST LOGIN          
Url: `/login/`
```
    {
        email,
        password
    }
```
### POST REGISTER       
Url: `/register/`
```
    {
        email,
        nickname,
        password
    }
```
### GET AVATAR         
Url: `/avatars/{avatar}`
```
        file
```
### UPLOAD AVATAR         
Url: `/upload/avatar/`
```
    if your id attribute === user ID -> file uploads
```
### ABOUT         
Url: `/about/`
```
    {
        message: "Hello, world!",
        successful: true,
    }
```

### POST EDIT USER      
Url: `/user/edit`

    if your id attribute === user ID -> you can edit

* field_type == **EMAIL**
```
{
    text
}
```
* field_type == **NICKNAME**
```
{
    text
}
```
* field_type == **PASSWORD**
```
{
    password,
}
```
### LOGOUT:           
Url: `/logout`
```
    if your id attribute === user ID -> you logout
```

