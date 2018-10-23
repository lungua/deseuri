DELIMITER ||

drop trigger cantRezultataDelete ||
CREATE TRIGGER cantRezultataDelete
before delete ON cantrezultata
FOR EACH ROW

BEGIN
    DECLARE wcantN double;
    DECLARE wcantO double;
    declare widMiscLuna integer;
    
    
    

    set wcantN=0.0;
    set wcantO=0.0;
    set widMiscLuna=0;
    
   
    set widMiscLuna = old.MiscariLuna_idMiscariLuna;
    

   
   update  miscariluna    set CantRezultata= CantRezultata-wcantO where idMiscariLuna=widMiscLuna;
   
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;
