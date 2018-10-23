DELIMITER ||

drop trigger cantRezultataUpdate ||
CREATE TRIGGER cantRezultataUpdate
AFTER Update ON cantrezultata 
FOR EACH ROW

BEGIN
    DECLARE wcantN double;
    DECLARE wcantO double;
    declare widMiscLuna integer;
    

    
    set wcantN=0.0;
    set wcantO=0.0;
    set widMiscLuna=0;
    set wcantN= new.cantitate;
    set wcantO= old.cantitate;
    set widMiscLuna = new.MiscariLuna_idMiscariLuna;
    

   
   update  miscariluna    set CantReciclate= CantReciclate-wcantO+wcantN where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;
