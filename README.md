# Blackjack FX

Thème : Le but, est de créer une application lourde autour du thème du blackjack la plus fidèle possible.

> !Lien du répos distant : [Github](https://github.com/Blackjack-Project/Blackjack-FX.git)

## Prérequis pour utiliser le projet

Pour lancer le projet il est nécéssaire d'accéder à la base.

## Diagramme des cas d'utilisation

![diagrammeCasUtilisation](assets/diagrammeCasUtilisation.svg)

## Diagramme de classe : Architecture MVVM + Domaine

![diagrammeDeClasses](assets/diagrammeDeClasses.svg)

## Maquette interface

![maquetteInterface](assets/maquetteInterface.svg)

## MCD

![mcd](assets/mcd.xlsx)



## Base de données





## Diagramme de séquence

```mermaid
sequenceDiagram
    participant Joueur
    participant Croupier
    participant Jeu

    Joueur->>Jeu: Place sa mise
    Croupier->>Jeu: Mélange les cartes
    Croupier->>Joueur: Donne 2 cartes
    Croupier->>Croupier: Reçoit 2 cartes (1 visible)

    alt Les 2 cartes du Joueur sont identiques
        Joueur->>Jeu: Demande à splitter
        Jeu->>Joueur: Crée 2 mains
        Joueur->>Jeu: Place une mise pour 2e main
    end

    alt Le Joueur veut doubler
        Joueur->>Jeu: Double sa mise
        Joueur->>Croupier: Demande une carte
        Croupier->>Joueur: Donne une carte
        Joueur->>Joueur: Termine son tour
    else
        loop Pour chaque main
            Joueur->>Joueur: Vérifie le total
            loop Tant que le Joueur tire
                Joueur->>Croupier: Demande une carte
                Croupier->>Joueur: Donne une carte
                Joueur->>Joueur: Met à jour le total
                alt Le total dépasse 21
                    Joueur->>Joueur: Perd (bust)
                end
            end
        end
    end

    Croupier->>Croupier: Révèle sa 2e carte
    loop Tant que le total < 17
        Croupier->>Jeu: Tire une carte
        Jeu->>Croupier: Donne une carte
        Croupier->>Croupier: Met à jour le total
    end

    loop Résultat pour chaque main du Joueur
        alt Croupier > 21
            Croupier->>Joueur: Gagne (Croupier bust)
        else
            alt Joueur > Croupier
                Croupier->>Joueur: Gagne
            else
                alt Joueur < Croupier
                    Croupier->>Joueur: Perd
                else
                    Croupier->>Joueur: Égalité (push)
                end
            end
        end
    end


```

