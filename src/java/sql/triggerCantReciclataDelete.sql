DELIMITER ||

drop trigger cantReciclataDelete ||
CREATE TRIGGER cantReciclataDelete
before delete ON cantreciclata 
FOR EACH ROW

BEGIN
    DECLARE wcantN double;
    DECLARE wcantO double;
    declare widMiscLuna integer;
    declare wagent varchar(255);
    
    set wagent ="";

    set wcantN=0.0;
    set wcantO=0.0;
    set widMiscLuna=0;
    
    set wcantO= old.cantitate;
    set widMiscLuna = old.MiscariLuna_idMiscariLuna;
    set wagent= old.agentEc;

   
   update  miscariluna    set CantReciclate= CantReciclate-wcantO where idMiscariLuna=widMiscLuna;
   update  miscariluna    set AgentElconomic=replace(AgentElconomic,wagent,"") where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;
