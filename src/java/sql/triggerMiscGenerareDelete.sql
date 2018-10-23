DELIMITER ||

drop trigger miscariGenerareDelete ||
CREATE TRIGGER miscariGenerareDelete
before delete ON miscarigenerare 
FOR EACH ROW

BEGIN
    DECLARE wcantN double;
    DECLARE wcantO double;
    declare widMiscLuna integer;
    set wcantN=0.0;
    set wcantO=0.0;
    set widMiscLuna=0;
    
    set wcantO= old.cantitate;
    set widMiscLuna = old.MiscariLuna_idMiscariLuna;


   
   update  miscariluna    set miscGenerare= miscGenerare-wcantO where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;
