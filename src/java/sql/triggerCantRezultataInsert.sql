DELIMITER ||

drop trigger cantRezultataInsert ||
CREATE TRIGGER cantRezultataInsert
AFTER INSERT ON cantrezultata
FOR EACH ROW

BEGIN
    DECLARE wcant double;
    declare widMiscLuna integer;
    
    

    set wcant=0.0;
    
    set widMiscLuna=0;
    set wcant= new.cantitate;
    set widMiscLuna = new.MiscariLuna_idMiscariLuna;
    

   
   update  miscariluna    set CantReciclate= CantReciclate+wcant where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;