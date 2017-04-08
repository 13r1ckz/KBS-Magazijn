<?php

?>
<html>
    <head>
        <title>test</title>
        <?php
        date_default_timezone_set('Europe/Amsterdam');
        ?>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        
    </head>
    <body>
        <form method="post">
                First name:<br>
            <input type="text" name="name" id="input1" class="input"/>
            <br>
            Last name:<br>
            <input type="text" name="lastname" id="input2" class="input"/>
            <br>    
                Adres:<br>
            <input type="text" name="adres" id="input3" class="input"/>
            <br>
                Postcode:<br>
            <input type="text" name="Postcode" id="input4" class="input"/>
            <br>
                Plaats:<br>
            <input type="text" name="Plaats" id="input5" class="input"/>
            <br>    
                Order:<br>
            <input type="text" name="Plaats" id="input6"/>

            <input type="button" id="addorder" value="+"/>       
            <br>

            <br>
            <br>
            <div id="preview">
                <div id="xmlh">
                    <span><</span>?xml version="1.0" encoding="UTF-8"?><br>
                    <span><</span>bestelling><br>
                        <span><</span>ordernummer><span><?php $digits = 4; echo rand(pow(10, $digits-1), pow(10, $digits)-1); ?></span><span><</span>/ordernummer><br>
                        <span><</span>klant><br>
                            <div><span><</span>voornaam><span class="input1" value=""></span><span><</span>/voornaam></div>
                            <div><span><</span>achternaam><span class="input2"></span><span><</span>/achternaam></div>
                            <div><span><</span>adres><span class="input3"></span><span><</span>/adres></div>
                            <div><span><</span>postcode><span class="input4"></span><span><</span>/postcode></div>
                            <div><span><</span>plaats><span class="input5"></span><span><</span>/plaats></div>
                            <div><span><</span>datum><span><?php echo date('d-m-y'); ?></span><span><</span>/datum></div>
                        <span><</span>/klant><br>
                            <div id="Content"></div>
                    <span><</span>/bestelling><br>
                    <br>
                    <br>
            </div>
            <span id="doc"></span>
            <br>
            <input type="button" id="doc" value="make doc"/> 
        </form>
        <script>      
        $('.input').keyup(function(){
            var $this = $(this);
            $('.'+$this.attr('id')+'').html($this.val());
        });
        $('input#addorder').click(function() {
            var text = $('#input6').val();
            if(text == ""){
                
            }
            else{
                $('<span><</span><span>artikelnr>' + text +  '</span><<span>/artikelnr></span><br>').appendTo('#Content');
                $('#input6').val('')
            }
        });
        
        $('input#doc').click(function(){
            var blank = " ";
            var xmlr = $('#xmlh').clone();
            $(xmlr).appendTo('#doc');
            window.location.href = "index.php?name=" + xmlr; 
        });
        </script>
        
        <?php
            echo $_GET['name'];                        
        ?>
    </body>
</html>
